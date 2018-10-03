# SugarOrmCipher

encrypt database with SugarOrm


i am using http://satyan.github.io/sugar/ 

Thanks to  <a href="http://www.twitter.com/snarayan">@snarayan</a>

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.golroz5950:SugarOrmCipher:Version_1.0.0'
	}



Step 3. build table


public class Note extends SugarRecord {

   public String title, note;


  
    public long time;



    // Default constructor is important!
    public Note() {
    }

    public Note(String title, String note, long time) {
        this.title = title;
        this.note = note;
        this.time = time;
        }
    }
    
    Step 4. use table
    
    
   
public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        intialize_Sugar();


    }

    private void intialize_Sugar(){
        SugarContext.setDbName("test.db");
        SugarContext.setDomainPakageName("com.DomainPakageName");
        SugarContext.setQueryLog(true);
        SugarContext.setVersion(1);
        SugarContext.setEncryptKey("password123456789");
        SugarContext.init(context);

    }

   

    @Override
    protected void onDestroy() {
        SugarContext.terminate();
        super.onDestroy();

    }

    public void btn_add(View view) {
        try {

            Note note = new Note( "test", "this is test",  System.currentTimeMillis() );
            note.save();
           
        } catch (Exception e) {
           
        }
    }
    
    }
    
