package owenparker.gui.menu_bar;

import owenparker.engine.HasDisplayableName;
import owenparker.gui.AccessorComponent;
import owenparker.gui.Launcher;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class ResourceMenuLister<T extends HasDisplayableName> {
	private final Consumer<T> onClick;
	private final List<T> resources;

	private BiConsumer<AbstractButton, T> componentModifier = (_, _) -> {};
	private Callable<AbstractButton> buttonGetter = JMenuItem::new;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private Function<T, Boolean> isSelected = _ -> false;

	public void setComponentModifier(BiConsumer<AbstractButton, T> componentModifier) {
		this.componentModifier = componentModifier;
	}

	public ResourceMenuLister(Consumer<T> onClick, List<T> resources) {
		this.onClick = onClick;
		this.resources = resources;
	}

	public void setButtonGetter(Callable<AbstractButton> getter) {
		this.buttonGetter = getter;
	}

	public void setButtonGroup(ButtonGroup buttonGroup, Function<T, Boolean> isSelected) {
		this.buttonGroup = buttonGroup;
		this.isSelected = isSelected;
	}

	public void listResources(AccessorComponent<JMenu> menuToAddTo) {
		for (T resource : this.resources) {
			try {
				AbstractButton button = this.buttonGetter.call();
				button.setText(resource.getDisplayableName());
				button.addActionListener(_ -> this.onClick.accept(resource));
				this.buttonGroup.add(button);
				if (this.isSelected.apply(resource))
					this.buttonGroup.setSelected(button.getModel(), true);
				this.componentModifier.accept(button, resource);
				menuToAddTo.getComponent().add(button);
			} catch (Exception e) {
				Launcher.errorWindow(e);
			}
		}
	}
}
