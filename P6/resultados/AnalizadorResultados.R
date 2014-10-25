compararCSV<-function(fileCSV,realCSV)
{

  archivo<-read.csv(fileCSV,skip=2)

  clases<-read.csv(realCSV,header=FALSE);
  
  print(summary(archivo[5]));
  print(summary(archivo[5]-clases[5]==0));
}

args <- commandArgs(trailingOnly = TRUE);
compararCSV(args[1],args[2]);