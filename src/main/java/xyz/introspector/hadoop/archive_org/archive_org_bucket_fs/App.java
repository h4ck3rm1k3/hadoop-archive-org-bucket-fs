package xyz.introspector.hadoop.archive_org.archive_org_bucket_fs;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.s3.S3Credentials;
import org.apache.hadoop.fs.s3.S3FileSystem;

import org.apache.hadoop.fs.s3native.NativeS3FileSystem;
import org.apache.hadoop.security.ProviderUtils;
import org.apache.hadoop.security.alias.CredentialProvider;
import org.apache.hadoop.security.alias.CredentialProviderFactory;
import org.jets3t.service.impl.rest.httpclient.RestStorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class App 
{
	

		//private static final Log log = LogFactory.getRootLogger();
	
    public static void main( String[] args )
    {     
    	//System.setProperty("org.apache.commons.logging.diagnostics.dest", "STDOUT");
    	//System.setProperty("log4j.rootCategory","DEBUG");
    	//System.setProperty("log4j.rootLogger","DEBUG");
    	//log.setLevel(Level.TRACE);
    
        System.out.println( "Hello World!" );
        S3Credentials s3Credentials = new S3Credentials();
        Configuration conf = new Configuration();
        String homedir = System.getenv().get("HOME");
        Path conf_file = new Path(homedir + "/.archive.org.properties");
        conf.addResource(conf_file);		        
        System.out.println(conf.getTrimmed("fs.s3a.access.key"));
        		
		try {
			URI U;
			U = new URI("s3://github_projects_data_sampler/");
			s3Credentials.initialize(U, conf);			
			System.out.println(U.getAuthority());	
		    //Jets3tNativeFileSystemStore store = new Jets3tNativeFileSystemStore();
		    NativeS3FileSystem fs = new NativeS3FileSystem();
		    //conf.setBoolean("fs.s3n.multipart.uploads.enabled", true);
		    //conf.setLong("fs.s3n.multipart.uploads.block.size", 64 * 1024 * 1024);
		    fs.initialize(U, conf);
		    boolean recursive=false;
		    Path p = new Path("/");
			fs.listFiles(p, recursive);
			fs.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
