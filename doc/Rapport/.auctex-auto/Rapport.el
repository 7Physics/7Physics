(TeX-add-style-hook
 "Rapport"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("report" "11pt")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("geometry" "margin=2.5cm") ("babel" "french") ("fontenc" "T1") ("titlesec" "explicit") ("inputenc" "utf8x")))
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
    "fontawesome5"))
 :latex)

