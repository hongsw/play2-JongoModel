package plugins;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

import org.jongo.Jongo;
import play.Configuration;
import play.Play;
import play.Plugin;
import play.Application;

import java.net.UnknownHostException;

public class JongoPlugin extends Plugin {

    private Application application;
    private Mongo mongo;
    private Jongo jongo;
    private DB db;
    
    public JongoPlugin(Application application) {
        this.application = application;
    }

    @Override
    public void onStart() {
        Configuration config = Configuration.root().getConfig("jongo");
        String uri = config.getString("mongodb.uri");
        String userid = config.getString("mongodb.userid");
        String password = config.getString("mongodb.password");
        try {
        	mongo = new MongoURI(uri).connect();
            db = mongo.getDB(config.getString("mongodb.dbname"));
        	db.authenticate(userid, password.toCharArray());
        	jongo = new Jongo(db);
        } catch (UnknownHostException e) {
    		throw new RuntimeException("Unable to create Mongo instance.", e);
		}
    }

    public Jongo getJongo() {
        return jongo;
    }

    public Mongo getMongo() {
        return mongo;
    }

    public DB getDb() {
        return db;
    }

    public static JongoPlugin getJongoPlugin() {
        play.Application app = Play.application();
        JongoPlugin plugin = app.plugin(JongoPlugin.class);
        if (plugin == null) {
            throw new RuntimeException("Unable to obtain Jongo Plugin. "
                    + "Check if plugin has been declared into your project in conf/play.plugins file. "
                    + "If not, please add line '20000:plugins.JongoPlugin'");
        }
        return plugin;
    }
}
