#include <stdio.h>
#include <stdlib.h>

main()
{
   int c;
   
   printf("Type 'y' to shutdown your computer(Literally):");
   while((c = getchar()) != EOF)
   {
      if(c == 'y')
	  {
	     system("C:\\WINDOWS\\System32\\shutdown -s");
	  }
   }
}