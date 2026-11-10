package owenparker.gui.export;

import owenparker.config.Configuration;
import owenparker.gui.ConfirmationWindow;
import owenparker.gui.GenericWindow;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.function.Consumer;

public class ExportWindow extends ConfirmationWindow<ExportConfig> {

	public ExportWindow(GlobalReference globalReference) {
		super(
				globalReference,
				new ExportConfig(globalReference),
				c -> c.exporter.get().export(c, globalReference.getAutomaton()),
				"Export"
		);
	}
}
