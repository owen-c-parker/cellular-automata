package owenparker.gui.export;

import owenparker.gui.GenericWindow;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ExportWindow extends GenericWindow {
    public ExportWindow(GlobalReference globalReference) {
        ExportConfig config = new ExportConfig(globalReference);

        setContentPane(new JPanel(new BorderLayout()));

        getContentPane().add(config.getPanel(), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        bottom.setBorder(new LineBorder(Color.BLACK, 1, false));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(_ -> dispose());
        bottom.add(cancelButton);

        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(_ -> {
            config.exporter.get().export(config, globalReference.getAutomaton());
            dispose();
        });
        bottom.add(exportButton);

        getContentPane().add(bottom, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
