package owenparker.gui.export;

import owenparker.gui.ConfirmationWindow;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.SimulationContext;

public class ExportWindow extends ConfirmationWindow<ExportConfig> {

	public ExportWindow() {
		super(
				new ExportConfig(),
				c -> c.exporter.get().export(c, SimulationContext.getAutomaton()),
				"Export"
		);
	}
}
