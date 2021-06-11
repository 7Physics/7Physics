(TeX-add-style-hook
 "Rapport"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("report" "11pt")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("geometry" "margin=2.5cm") ("babel" "french") ("fontenc" "T1") ("titlesec" "explicit") ("inputenc" "utf8x")))
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "path")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "url")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "nolinkurl")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperbaseurl")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperimage")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperref")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "href")
   (add-to-list 'LaTeX-verbatim-macros-with-delims-local "path")
   (TeX-run-style-hooks
    "latex2e"
    "titlepage"
    "report"
    "rep11"
    "geometry"
    "babel"
    "fontenc"
    "titlesec"
    "times"
    "fancyhdr"
    "graphicx"
    "ucs"
    "inputenc"
    "awesomebox"
    "fontawesome5"
    "hyperref"
    "rotating"))
 :latex)

