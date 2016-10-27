(merge-env!
 :source-paths #{"src/cljs"}
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

(deftask dev
  []
  (comp
   (cljs)
   (serve)
   (wait)))

