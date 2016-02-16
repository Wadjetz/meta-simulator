package fr.esgi.meta.microorganism;

import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.microorganism.factions.*;
import fr.esgi.meta.pattern.factory.Factory;

public class MicroorganismFactory extends Factory<Faction, String> {
    @Override
    public Faction getInstance(String type) {
        switch (type) {
            case "virus":
                return new VirusFaction(type);
            case "bacterium":
                return new BacteriumFaction(type);
            case "cells":
                return new CellsFaction(type);
            case "antibiotics":
                return new AntibioticsFaction(type);
            case "white-globules":
                return new WhiteGlobulesFaction(type);
            case "red-blood-cells":
                return new RedBloodCellsFaction(type);
            default:
                throw new RuntimeException("Unknown Faction");
        }
    }
}
