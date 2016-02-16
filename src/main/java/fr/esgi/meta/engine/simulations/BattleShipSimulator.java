package fr.esgi.meta.engine.simulations;
import fr.esgi.meta.battleship.unit.Boats;
import fr.esgi.meta.engine.units.Unit;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 626 on 15/02/2016.
 */
public class BattleShipSimulator extends Simulator{

    Map<Integer,List<Integer>> allieMap = new HashMap<>();
    Map<Integer,List<Integer>> axeMap = new HashMap<>();
    Map<String,Boolean>allieLife = new HashMap<>();
    Map<String,Boolean>axeLife = new HashMap<>();

    @Override
    public void run() {

        System.out.println(name + " simulation run");



        List<Unit> allUnits = factions.stream().<Unit>flatMap(f -> f.getUnits().stream()).collect(Collectors.toList());
        board.randomDispatch(allUnits);
        int i=1;
        populateMap();
        Random rand = new Random();
        while(i>0)
        {
            int x =0;
            int y =0;

            int choice = 0, selected = 0;
            if(i%2 == 0) {
                choice = rand.nextInt(allUnits.size() / 2 );
                List<Integer> keys      = new ArrayList<Integer>(axeMap.keySet());
                if (!keys.isEmpty()) {
                    x = keys.get(rand.nextInt(keys.size()));
                    if(axeMap.get(x).size()>0)
                        y = axeMap.get(x).get(rand.nextInt(axeMap.get(x).size()));
                }
            }
            else{
                selected = 1;
                choice = rand.nextInt(allUnits.size() / 2 );
                List<Integer> keys2      = new ArrayList<Integer>(allieMap.keySet());
                if (!keys2.isEmpty()) {
                    x = keys2.get(rand.nextInt(keys2.size()));
                    if(allieMap.get(x).size()>0)
                        y = allieMap.get(x).get(rand.nextInt(allieMap.get(x).size()));
                }
            }

            boolean find = getUnitZone(x,y);

            if(find == true){
                attack(selected,choice,x,y);
                try{
                    printMap(i);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            i++;

            if (checkAllieLife()) {
                System.out.println("Axe a gagne");
                break;
            }
            if (checkAxeLife()) {
                System.out.println("Allie a gagne");
                break;
            }
        }
        //System.out.println(board);

    }

    private boolean getUnitZone(int abscisse, int ordonnee){
        try{
            Unit unit = getBoard().getZones()[abscisse][ordonnee].getUnit().get();
        }catch(Exception e)
        {
            return false;
        }
        return true;
    }

    private void attack(int select, int boats,int abscisse, int ordonnee){
        Boats sangoku = (Boats) factions.get(select).getUnits().get(boats);
        Boats vegeta = (Boats) getBoard().getZones()[abscisse][ordonnee].getUnit().get();
        Boats trunk ;
        int ennemy =0;
        if(select == 0 )
            ennemy = 1;

        if(sangoku.getType().equals("croiseur")) {
            if (vegeta.getType().equals("porte-avions")) {
                System.out.println(sangoku.getType() +" attaque ====> "+vegeta.getType());
                boolean find ;
                int index = indexBoat("fregate", ennemy);
                trunk = (Boats) factions.get(ennemy).getUnits().get(index);


                if (trunk.getLife() <= 0){
                    updateLife(ennemy,"fregate");
                    find = false;
                }
                else
                {
                    trunk.setLife(trunk.getLife() - sangoku.getDamages());
                    find = true;
                }

                if(!find){
                    index = indexBoat("porte-avions",ennemy);
                    Boats modif = (Boats) factions.get(ennemy).getUnits().get(index);
                    modif.setLife(modif.getLife()-sangoku.getDamages());
                    if (modif.getLife() <= 0){
                        updateLife(ennemy,"porte-avions");
                    }

                }
            } else if (vegeta.getType().equals("fregate")) {
                System.out.println(sangoku.getType() +" attaque ====> "+vegeta.getType());
                vegeta.setLife(vegeta.getLife() - sangoku.getDamages());
                int index = indexBoat("fregate", ennemy);
                Boats modif = (Boats) factions.get(ennemy).getUnits().get(index);
                modif.setLife(vegeta.getLife());
                if(vegeta.getLife() <=0){
                    updateLife(ennemy,"fregate");
                }
            }
        }
        else if(sangoku.getType().equals("fregate")) {
            if (vegeta.getType().equals("sous-marin")) {
                System.out.println(sangoku.getType() +" attaque ====> "+vegeta.getType());
                vegeta.setLife(vegeta.getLife() - sangoku.getDamages());
                int index =indexBoat("sous-marin", ennemy);
                Boats modif = (Boats) factions.get(ennemy).getUnits().get(index);
                modif.setLife(vegeta.getLife());
                if(modif.getLife() <=0){
                    updateLife(ennemy,"sous-marin");
                }
            }
            else if (vegeta.getType().equals("croiseur")) {
                System.out.println(sangoku.getType() +" attaque ====> "+vegeta.getType());
                vegeta.setLife(vegeta.getLife() - sangoku.getDamages());
                int index = indexBoat("croiseur", ennemy);
                Boats modif = (Boats) factions.get(ennemy).getUnits().get(index);
                modif.setLife(vegeta.getLife());
                if(modif.getLife() <=0){
                    updateLife(ennemy,"croiseur");
                }
            }
        }
        else if(sangoku.getType().equals("sous-marin")) {
            if (vegeta.getType().equals("sous-marin")) {
                System.out.println(sangoku.getType() +" attaque ====> "+vegeta.getType());
                vegeta.setLife(vegeta.getLife() - sangoku.getDamages());
                int index = indexBoat("sous-marin", ennemy);
                Boats modif = (Boats) factions.get(ennemy).getUnits().get(index);
                modif.setLife(vegeta.getLife());
                if(modif.getLife() <=0){
                    updateLife(ennemy,"sous-marin");
                }

            } else if (vegeta.getType().equals("croiseur")) {
                System.out.println(sangoku.getType() +" attaque ====> "+vegeta.getType());
                vegeta.setLife(vegeta.getLife() - sangoku.getDamages());
                int index = indexBoat("croiseur", ennemy);
                Boats modif = (Boats) factions.get(ennemy).getUnits().get(index);
                modif.setLife(vegeta.getLife());
                if(modif.getLife() <=0){
                    updateLife(ennemy,"croiseur");
                }
            }
            else if (vegeta.getType().equals("porte-avions")) {
                System.out.println(sangoku.getType() +" attaque ====> "+vegeta.getType());
                boolean find;
                int index =indexBoat("fregate", ennemy);
                trunk = (Boats) factions.get(ennemy).getUnits().get(index);


                if (trunk.getLife() <= 0){
                    updateLife(ennemy,"fregate");
                    find = false;
                }
                else {
                    find = true;
                    trunk.setLife(trunk.getLife() - sangoku.getDamages());
                }

                if(!find){

                        index = indexBoat("porte-avions",ennemy);
                        Boats modif = (Boats) factions.get(ennemy).getUnits().get(index);
                        modif.setLife(modif.getLife()-sangoku.getDamages());
                        if(modif.getLife() <=0){
                            updateLife(ennemy, "porte-avions");
                        }
                    }
            }
        }
        else
            System.out.println(sangoku.getType() +" attaque ====> dans le vide");
    }

    private void populateMap(){
        for (int i=0; i< getBoard().getHeight();i++) {
            allieMap.put(i, new ArrayList<>());
            axeMap.put(i, new ArrayList<>());
            for (int j = 0; j < getBoard().getWidth()/2; j++) {
                boolean find = getUnitZone(i,j);
                if(find)
                    allieMap.get(i).add(j);
            }
            for (int k=getBoard().getWidth()/2; k < getBoard().getWidth();k++){
                boolean findu = getUnitZone(k,i);
                if(findu)
                    axeMap.get(i).add(k);
            }
        }
        for (Unit unit : factions.get(0).getUnits())
        {
            allieLife.put(unit.getType(),true);
        }
        for (Unit unit : factions.get(1).getUnits())
        {
            axeLife.put(unit.getType(),true);
        }
    }

    private void printMap(int turn) throws IOException{
        System.out.println("\n\nTurn " + turn + " ------------------------------------------------------");
        System.out.println(board);

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int indexBoat(String nom, int factionNumber){
        int i=-1;
        for (Unit unit : factions.get(factionNumber).getUnits())
        {
            if(unit.getType().equals(nom))
                return ++i;
            i++;
        }
        return -1;
    }

    private void updateLife(int selected, String key){
        if(selected == 0)
            allieLife.put(key,false);
        else
            axeLife.put(key,false);
    }
    private boolean checkAllieLife(){
        int i = 0;
        for (Map.Entry<String, Boolean> entry : allieLife.entrySet())
        {
            if(!entry.getValue())
                i++;
        }
        if(i==4)
            return true;
        return false;
    }
    private boolean checkAxeLife(){
        int i = 0;
        for (Map.Entry<String, Boolean> entry : axeLife.entrySet())
        {
            if(!entry.getValue())
                i++;
        }
        if(i==4)
            return true;
        return false;
    }
}
