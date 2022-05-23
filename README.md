# NomNoml-Graph-Generator (cli)

Generate automatically Nomnoml code for drawing Graph given as input in a new .nomnoml file.

<br>

#### (For the GUI version please switch to the "FxApp" branch or have a look at the GUI releases)

<br>

---

### Command is Run like so

  ```sh
   java --enable-preview -jar NomNomGenerator.jar <arguments>
  ```  
`<arguments>` is of the form : `GraphTest3 # 1,2 ; 2,3 ; 1,4 ; 4,6 ; 3,6` where `GraphTest3` is the title of the graph and future fileName, and after the `#`, the list of edges.

  PS: To get the jar, download the release.
