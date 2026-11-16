package owenparker.gui.sidebar;

import owenparker.gui.CursorMode;
import owenparker.gui.context.DisplayContext;

import java.awt.*;

public abstract class CursorModeButton extends SideBarButton {
	public CursorModeButton(SideBar sideBar) {
		super(sideBar);

		addActionListener(_ -> {
			DisplayContext.setCurrentCursorMode(getCursorMode());
			refreshSelection();
		});

		setToolTipText(getCursorMode().getTooltip());
	}

	@Override
	protected void draw(Graphics g) {
		g.drawImage(getImage(), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
	}

	protected abstract Image getImage();

	protected abstract CursorMode getCursorMode();

	@Override
	public void refreshSelection() {
		setBorder(
				DisplayContext.getCurrentCursorMode().equals(getCursorMode()) ?
						getSelectedBorder() :
						getDeelectedBorder()
		);
	}
}
