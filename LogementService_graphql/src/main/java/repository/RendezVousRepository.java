package repository;

import entity.Logement;
import entity.RendezVous;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class RendezVousRepository {
    public static List<RendezVous> listeRendezVous;
    LogementRepository logementMetier=new LogementRepository();
    public RendezVousRepository(){
        listeRendezVous=new ArrayList<RendezVous>();
        listeRendezVous.add(new RendezVous(1, "31-10-2015","15:30", logementMetier.getLogementsByReference(4), "55214078"));
        listeRendezVous.add(new RendezVous(2, "20-12-2015","9:00", logementMetier.getLogementsByReference(1), "21300811"));
        listeRendezVous.add(new RendezVous(3, "17-09-2015","9:15", logementMetier.getLogementsByReference(4), "98102102"));
    }
    public Logement getLogementByRDV(int idRDV) {
        Logement logement = null;
        for(RendezVous r:listeRendezVous){
            if(r.getId()== idRDV)
                return r.getLogement();
        }
        return logement;
    }
    public boolean updateRendezVous(RendezVous rendezVous){
        int index=listeRendezVous.indexOf(rendezVous);
        if(index!=-1){
            Logement logement=logementMetier.getLogementsByReference(rendezVous.getLogement().getReference());
            if(logement!=null){
                rendezVous.setLogement(logement);
                listeRendezVous.set(index, rendezVous);
                return true;
            }
        }
        return false;
    }

    public boolean addRendezVous(RendezVous rendezVous){

        int refLogement=rendezVous.getLogement().getReference();

        Logement logement=logementMetier.getLogementsByReference(refLogement);

        if(logement!=null){
            rendezVous.setLogement(logement);
            return listeRendezVous.add(rendezVous);}
        return false;
    }
    public List<RendezVous> getListeRendezVous() {
        return listeRendezVous;
    }

    public void setListeRendezVous(List<RendezVous> listeRendezVous) {
        this.listeRendezVous = listeRendezVous;
    }
    public List<RendezVous> getListeRendezVousByLogementReference(int reference) {
        List<RendezVous> liste=new ArrayList<RendezVous>();
        for(RendezVous r:listeRendezVous){
            if(r.getLogement().getReference()==reference)
                liste.add(r);
        }
        return liste;
    }
    public RendezVous getRendezVousById(int id){
        RendezVous rendezVous=null;
        for(RendezVous r:listeRendezVous){
            if(r.getId()==id)
                rendezVous=r;
        }
        return rendezVous;
    }
    public boolean deleteRendezVous(int id){
        Iterator<RendezVous> iterator=listeRendezVous.iterator();
        while(iterator.hasNext()){
            RendezVous r=iterator.next();
            if(r.getId()==id){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public boolean updateRendezVous(int idRendezVous, RendezVous updatedRendezVous) {
        for (int i = 0; i < listeRendezVous.size(); i++) {
            RendezVous r = listeRendezVous.get(i);
            if (r.getId() == idRendezVous) {

                Logement logement = logementMetier.getLogementsByReference(updatedRendezVous.getLogement().getReference());
                if (logement != null) {

                    listeRendezVous.set(i, updatedRendezVous);
                    return true;
                }
            }
        }
        return false;
    }

}
