(merge-env!
 ;; If you put cljs files in :source-paths, then only the
 ;; compiled js will be placed into the fileset.
 :source-paths #{"src/cljs"}
 ;; If you put cljs files in :resource-paths, the cljs source code
 ;; will also be placed in the fileset along with the compiled js
 :resource-paths #{"resources/public"}
 :dependencies 
 '[[org.clojure/clojure       "1.9.0-alpha10"]
   [org.clojure/clojurescript "1.9.229"]

   [adzerk/boot-cljs        "1.7.228-1"           ] 
   [pandeiro/boot-http      "0.6.3"  :scope "test"]

   [reagent  "0.5.1" :exclusions [cljsjs/react]]
   [cljsjs/react-with-addons "0.14.7-0"]
])

(require '[adzerk.boot-cljs             :refer [cljs]]
         '[pandeiro.boot-http           :refer [serve]])

(task-options!
 pom {:project 'upgradingdave/boot-cljs-example
      :version "0.1.0"
      :description "Example of how to configure boot cljs"
      :license {"The MIT License (MIT)" 
                "http://opensource.org/licenses/mit-license.php"}})

(deftask advanced 
  []
  (comp
   ;; Make sure that the names inside the list of :ids match exactly
   ;; to the relative path and filename of corresponding *.cljs.edn
   (cljs :optimizations :advanced
         :ids #{"compiled/main"
                "compiled/simple"})
   (target)))

(deftask dev
  []
  (comp
   (advanced)
   ;; By default, cljs task looks for *.cljs.edn files in order to
   ;; know how to compile cljs to js. If no *.cljs.edn files exist, it
   ;; will create a main.cljs.edn file under the hood and produce
   ;; main.js by default
   (cljs :ids #{"main" 
                "js/simple"})
   ;; The target task write write all the files from the fileset into
   ;; the `target` directory by default
   (target)
   ;; By default, the serve task starts a jetty server listening on
   ;; port 3000 serving files in the fileset. 
   (serve)
   ;; The wait task is necessary to tell boot to hang out and let the
   ;; jetty server run until you kill the process
   (wait)))

