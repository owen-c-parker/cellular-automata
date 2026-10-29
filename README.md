# Cellular Automaton Simulator

This is a program for simulating [cellular automata](https://en.wikipedia.org/wiki/cellular_automata/). Some
[sample automata](#sample-automata) are included.

### Controls

| Input               | Action                |
|:--------------------|:----------------------|
| Scroll down         | Zoom out              |
| Scroll up           | Zoom in               |
| Hold mouse button 3 | Panning               |
| Left click          | Draw cells            |
| Right click         | Remove cells          |
| Hold shift          | Draw in straight line |

# Sample Automata

 - [Conway's Game of Life](/src/main/java/automata/CGOL.java) ([Wikipedia](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life))
 - [Wireworld](/src/main/java/automata/Wireworld.java) ([Wikipedia](https://en.wikipedia.org/wiki/Wireworld))
 - [Rule 110](/src/main/java/automata/Rule110.java) ([Wikipedia](https://en.wikipedia.org/wiki/Rule_110))
 - [Knight CGOL](/src/main/java/automata/KnightCGOL.java)
   - Like Conway's Game of Life, but the neighborhood is like how a knight in Chess moves

# Highlights

 - Easily extensible
 - Friendly GUI