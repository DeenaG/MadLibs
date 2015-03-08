package project.madlibs;

public class MadLibs {
	
	private int id;
	private String story;
	
	public MadLibs(){
		this.id = 0;
		this.story = null;
	}
	
	public MadLibs(int id, String story){
		this.id = id;
		this.story = story;
	}
	
	public MadLibs (String story){
		this.story = story;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setStory(String story) {
		this.story = story;
	}

	public int getId() {
		return this.id;
	}

	public String getStory() {
		return this.story;
	}
	
	public String getMadLib(String noun, String verb, String adj, String adv){
		String madLib = this.story;
		try{
			madLib = madLib.replaceAll("noun", noun).replaceAll("adverb", adv).replaceAll("adjective", adj).replaceAll("verb", verb);
		}
		catch(Exception e){
			return "Mad Lib is not in proper format. Read the directions and try again";
		}
		
		return madLib;
	}

}
