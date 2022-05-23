# NomNoml-Graph-Generator

Generate automatically Nomnoml code for drawing Graph given as input in a new .nomnoml file

---

For the best experience, please ensure you have the npm package installed [nomnoml-cli](https://www.npmjs.com/package/nomnoml-cli) as it will compile the nomnoml code into pdf.  

If you don't have the package or don't have npm, the program will crash but you'll still going to get the generated file without any problem. You're just going to have an error message that you can ignore.

---

#### For the GUI app, you can ignore the following.


### CLI is Run like so

  ```sh
   java --enable-preview -jar NomNomGenerator.jar <arguments>
  ```  
`<arguments>` is of the form : `GraphTest3 # 1,2 ; 2,3 ; 1,4 ; 4,6 ; 3,6` where `GraphTest3` is the title of the graph and future fileName, and after the `#`, the list of edges.

  PS: To get the jar, download the release.
