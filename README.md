# Cellular Automaton Simulator

This is a program for simulating [cellular automata](https://en.wikipedia.org/wiki/cellular_automata/). Some
[sample automata](src/main/java/owenparker/automata) are included.

## Controls

| Input               | Action  |
|:--------------------|:--------|
| Scroll down         | Zoom    |
| Hold mouse button 3 | Panning |

### Pencil

| Input               | Action                |
|:--------------------|:----------------------|
| Left click          | Draw cells            |
| Right click         | Remove cells          |

### Select area

| Input          | Action             |
|:---------------|:-------------------|
| Click and drag | Select area        |
| Right click    | Copy selected area |

### Rectangle

| Input          | Action         |
|:---------------|:---------------|
| Click and drag | Fill rectangle |

# Plugins

Plugins can be installed easily. They come in the form of `.jar`
files and are simply put into the `plugins` directory that you
can find a link to inside the program.

Writing plugins is fairly simple. An example plugin is available
[here](https://github.com/owen-c-parker/sample-automata-plugin).
You just need to include the JAR for this release as a dependency.