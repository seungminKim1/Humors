package comm;

public class ActionForward {
	private boolean isRedirect = false;
	private String path = null;
	
	/*public ActionForward(String path,boolean isRedirect) {
		this.path = path;
		this.isRedirect = isRedirect;
	}*/
	public boolean isRedirect() {
		return isRedirect;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setRedirect(boolean b) {
		isRedirect = b;
	}
	
	public void setPath(String string) {
		path = string;
	}
}
