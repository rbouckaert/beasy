package methods;


import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import beast.app.beauti.Beauti;
import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.InputFilter;
import beast.app.util.Application;
import beast.core.BEASTInterface;
import beast.core.MCMC;
import beast.core.Runnable;
import beast.core.util.Log;
import beast.util.XMLParser;
import methods.implementation.BEASTObjectMethodsText;

public class MethodsServer extends Runnable {
	BeautiDoc beautiDoc;
	Beauti beauti;
	String html;
	List<Phrase> m;
	XML2Text xml2textProducer;
	
	File tmpFile = null;
	File file = null;

	ModelEditor me = new ModelEditor(true);
	
	public MethodsServer() {
		beautiDoc = new BeautiDoc();
		beautiDoc.beautiConfig = new BeautiConfig();
		beautiDoc.beautiConfig.initAndValidate();
		beauti = new Beauti(beautiDoc);
	}

	public MethodsServer(BeautiDoc doc) {
		beautiDoc = doc;
		beauti = new Beauti(beautiDoc);
	}
	
    class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
    		Log.warning.println(t.getRequestMethod());
        	String request = t.getRequestURI().toString().replaceAll("%20", " ");
        	Log.warning.println(">>" + request + "<<");
        	
            
        	me.handleCmd(request, beautiDoc, null);
        	String response = null;
            
    		try {
    			response = processCmd(request);
    			if (response == null) {
    				response = "null";
    			}
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
    		} catch (Exception e) {
    			e.printStackTrace();
                t.sendResponseHeaders(200, e.getMessage().length());
                OutputStream os = t.getResponseBody();
                os.write(e.getMessage().getBytes());
                os.close();
    		}
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

		private String processCmd(String request) throws Exception {
			XML2Text xml2textProducer = new XML2Text(beautiDoc);
			xml2textProducer.initialise((MCMC) beautiDoc.mcmc.get());
			List<Phrase> m = xml2textProducer.getPhrases();
			
			String html = XML2HTMLPaneFX.header + Phrase.toHTML(beautiDoc, m) + XML2HTMLPaneFX.footer + "</body>\n</html>";
			
	        FileWriter outfile = new FileWriter("/tmp/index.html");
	        outfile.write(html);
	        outfile.close();
	        return html;
		}
    }
    
	@Override
	public void initAndValidate() {
	}

	@Override
	public void run() throws Exception {
        HttpServer server = null;
        int port = 8000;
        boolean portFound = false;
        InetSocketAddress add;
        do {
        	try {
        		add = new InetSocketAddress(port);
        		server = HttpServer.create(add, 0);
        		portFound = true;
        	} catch (BindException e) {
        		port++;
        	}
        } while (!portFound || port == 9000);
        if (!portFound) {
        	throw new RuntimeException("Cannot find port available in range 8000 - 9000 -- perhaps there is some security software preventing to acces these ports?");
        }
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        
        Desktop.getDesktop().browse(new URI("http://127.0.0.1:" + port + "/"));
        // server.stop(0);
        
    }

    public static void main(String[] args) throws Exception {
		MCMC mcmc = null;
		MethodsServer server = new MethodsServer();
		BeautiDoc beautiDoc = server.beautiDoc;
		if (args.length > 0) {
			File file = new File(args[0]);
			beautiDoc.setFileName(file.getAbsolutePath());
			String xml = BeautiDoc.load(file);
			int i = xml.indexOf("beautitemplate=");
			if (i > 0) {
				i += 15;
				char c = xml.charAt(i);
				i++;
				int start = i;
				while (xml.charAt(i) != c) {
					i++;
				}
				String template = xml.substring(start, i);
				if (!template.endsWith("xml")) {
					template = template + ".xml";
				}
				beautiDoc.loadNewTemplate(template);
			} else {
				beautiDoc.loadNewTemplate("Standard.xml");
			}
			
			
			XMLParser parser = new XMLParser();
			mcmc = (MCMC) parser.parseFile(file);
		} else {
			mcmc = (MCMC) beautiDoc.mcmc.get();
		}

		beautiDoc.mcmc.setValue(mcmc, beautiDoc);
		for (BEASTInterface o : InputFilter.getDocumentObjects(beautiDoc.mcmc.get())) {
			if (o != null) {
				beautiDoc.registerPlugin(o);
			}
		}
		beautiDoc.determinePartitions();
		BEASTObjectMethodsText.setBeautiCFG(beautiDoc.beautiConfig);
		
		MethodsText.initNameMap();
		server.run();
    	//new Application(server, "Methods server", args);
    }
}

