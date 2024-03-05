package data.model;

public record InternetCat(String[] tags, String mimeType, int size, String createdAt, String editedAt, String _id)
{
	public InternetCat()
	{
		this(new String[] {}, "", 0, "", "", "");
	}
}
