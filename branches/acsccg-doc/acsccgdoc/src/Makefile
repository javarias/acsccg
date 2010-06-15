CC=latex
PS=dvips
PDF=pdflatex
PDF2=dvipdf
RM=rm
CP=cp
FILE=ACSCCGDocumentation
# Change the Document Name HERE!
#OFF=ALMA-BBB-CCC-A-DDD
OFF=ACSCCGDocumentation

all: design publish distclean clean
 
design: 
	$(CC) $(FILE).tex
	$(CC) $(FILE).tex
	#$(PDF2) -sPAPERSIZE=a4 $(FILE).dvi
	$(PDF2)  -sPAPERSIZE=a4 $(FILE).dvi
	$(PS) $(FILE).dvi -o $(FILE).ps

clean:
	$(RM) -f *.aux *.toc *.log *.lof *~

distclean: clean
	$(RM) -f *.dvi *.pdf *.ps

publish:
	mkdir -p ../publish
	$(CP) $(FILE).pdf ../publish/$(OFF).pdf
