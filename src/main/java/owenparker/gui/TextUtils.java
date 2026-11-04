package owenparker.gui;

public class TextUtils {
	public static String convertToHTML(String string) {
		return String.format("<html>%s<html>", string
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;")
				.replaceAll("\n", "<br>")
				.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
		);
	}
}
