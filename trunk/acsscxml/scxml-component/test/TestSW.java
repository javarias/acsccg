package alma.scxmlCmp;
import alma.scxmlCmp.StopWatch;

class TestSW
{  
	private StopWatch Cronometro;
        public static void main(String args[])
        {
           StopWatch Cronometro = new StopWatch();
	   Cronometro.fireEvent(StopWatch.EVENT_START);
	   System.out.println("Cronometro iniciado");	  
        }
}

