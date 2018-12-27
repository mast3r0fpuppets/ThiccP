
public class FileDetails {
	private String nomefich;
	private long bytesfich;
	
	public FileDetails(String nomefich, long bytesfich) {
		this.nomefich = nomefich;
		this.bytesfich = bytesfich;
	}

	@Override
	public String toString() {
		return nomefich +" , " + String.valueOf(bytesfich);
		
	}
	public String getName() {
		return nomefich;
	}
	

}
