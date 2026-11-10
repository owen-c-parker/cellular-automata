package owenparker.gui.sidebar;

import owenparker.gui.CursorMode;

import java.awt.*;

public abstract class CursorModeButton extends SideBarButton {
	public CursorModeButton(SideBar sideBar) {
		super(sideBar);

		getComponent().addActionListener(_ -> {
			this.globalReference.setCurrentCursorMode(getCursorMode());
			refreshSelection();
		});

		getComponent().setToolTipText(getCursorMode().getTooltip());
	}

	@Override
	protected void draw(Graphics g) {
		g.drawImage(getImage(), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
	}

	protected abstract Image getImage();

	protected abstract CursorMode getCursorMode();

	@Override
	public void refreshSelection() {
		getComponent().setBorder(
				this.globalReference.getCurrentCursorMode().equals(getCursorMode()) ?
						getSelectedBorder(this.globalReference) :
						getDeelectedBorder(this.globalReference)
		);
	}
}
