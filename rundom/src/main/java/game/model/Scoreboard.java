package game.model;

import java.util.ArrayList;

public class Scoreboard {

    private Player root;
    ArrayList<Pair<String, Double>> players = new ArrayList<>();
    public Scoreboard() {

    }

    public void insert(Player player, Player current) {

        if (player.getScore() == current.getScore()) {
            //si es igual, en este caso lo guardamos en el arraylist de nombres del nodo con el mismo score
            current.addArray(player.getName());

        }
        if (player.getScore() < current.getScore()) {
            //izauierda
            if (current.getLeft() != null) {
                insert(player, current.getLeft());
            } else {
                current.setLeft(player);
            }
        } else if (player.getScore() > current.getScore()) {
            //derecha
            if (current.getRight() != null) {
                insert(player, current.getRight());
            } else {
                current.setRight(player);
            }
        }
    }

    public ArrayList<Pair<String, Double>> printScore(Player current, int n) {

        if (current == null) {
            return players;
        }
        printScore(current.getRight(), n);
        players.add(new Pair<>(current.getName(),current.getScore()));
        if (!current.empty()) {
            for(String x : current.getNames()){
                players.add(new Pair<>(x,current.getScore()));
            }
        }

        n++;

        return printScore(current.getLeft(), n);
    }

    public Player getRoot() {
        return root;
    }

    public void setRoot(Player root) {
        this.root = root;
    }

}

