compararCSV<-function(fileCSV,realCSV)
{

  archivo<-read.csv(fileCSV,skip=2)
  
  clases<-read.csv(realCSV,header=FALSE);
  
  clase1<-median(archivo$clase[1:50]);
  clase2<-median(archivo$clase[50:100]);
  clase3<-median(archivo$clase[101:150]);
  
  archivo$clase=chartr(archivo$clase,old=paste(clase1,clase2,clase3,sep=""),new="123");
  archivo$clase=as.numeric(archivo$clase);
  
  print(summary(archivo[5]));
  print(summary(archivo[5]-clases[5]==0));
}

args <- commandArgs(trailingOnly = TRUE);
compararCSV(args[1],args[2]);