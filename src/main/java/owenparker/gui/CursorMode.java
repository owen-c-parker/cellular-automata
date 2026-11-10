package owenparker.gui;

import owenparker.engine.ImageHelper;
import owenparker.gui.sidebar.CursorModeButton;
import owenparker.gui.sidebar.SideBar;

import java.awt.*;

public enum CursorMode {
	SELECT("Select area to copy", ImageHelper.readImageFromResources("images/gui/selectArea.png")),
	PENCIL("Draw", ImageHelper.readImageFromResources("images/gui/pencil.png")),
	RECTANGLE("Fill rectangle", ImageHelper.readImageFromResources("images/gui/rectangle.png"));

	private final String tooltip;
	private final Image image;

	CursorMode(String tooltip, Image image) {
		this.tooltip = tooltip;
		this.image = image;
	}

	public String getTooltip() {
		return this.tooltip;
	}

	public CursorModeButton getButton(SideBar sideBar) {
		return new CursorModeButton(sideBar) {
			@Override
			protected Image getImage() {
				return CursorMode.this.image;
			}

			@Override
			protected CursorMode getCursorMode() {
				return CursorMode.this;
			}
		};
	}
}
