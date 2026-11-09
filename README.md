# Cellular Automaton Simulator

This is a program for simulating [cellular automata](https://en.wikipedia.org/wiki/cellular_automata/). Some
[sample automata](src/main/java/owenparker/automata) are included.

### Controls

| Input               | Action                |
|:--------------------|:----------------------|
| Scroll down         | Zoom                  |
| Hold mouse button 3 | Panning               |
| Left click          | Draw cells            |
| Right click         | Remove cells          |
| Hold shift          | Draw in straight line |

# Plugins

Plugins can be installed easily. They come in the form of `.jar`
files and are simply put into the `plugins` directory that you
can find a link to inside the program.

Writing plugins is fairly simple. An example plugin is available
[here](https://github.com/owen-c-parker/sample-automata-plugin).
You just need to include the JAR for this release as a dependency.