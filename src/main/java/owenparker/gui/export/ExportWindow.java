package owenparker.gui.export;

import owenparker.gui.ConfirmationWindow;
import owenparker.gui.GlobalReference;

public class ExportWindow extends ConfirmationWindow<ExportConfig> {

	public ExportWindow(GlobalReference globalReference) {
		super(
				new ExportConfig(globalReference),
				c -> c.exporter.get().export(c, globalReference.getAutomaton()),
				"Export"
		);
	}
}
