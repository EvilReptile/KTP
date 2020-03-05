package cham.Lab_3;

import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    
    private HashMap<Location, Waypoint> open_waypoint_list = new HashMap<Location, Waypoint>();
    private HashMap<Location, Waypoint> close_waypoint_list = new HashMap<Location, Waypoint>();


    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Этот метод просматривает все открытые путевые точки и возвращает путевую точку
     * с минимальной общей стоимостью. Если нет открытых путевых точек, этот метод
     * возвращает <code> null </ code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
    	Waypoint output = (Waypoint)open_waypoint_list.values().toArray()[0];
    	float cost = output.getTotalCost();
    	
        if(numOpenWaypoints() != 0) {
        	for(Map.Entry<Location, Waypoint> entry : open_waypoint_list.entrySet()) {
        		
        		if(cost >= entry.getValue().getTotalCost()) {
        			output = entry.getValue();
        			cost = output.getTotalCost();
        		}
        	}
        }
        return output;
    }

    /**
     * Этот метод добавляет путевую точку к (или потенциально обновляет путевую точку уже
     * в) коллекция "открытых путевых точек". Если там уже нет открытого
     * путевая точка на месте новой путевой точки, тогда новая путевая точка просто
     * добавлено в коллекцию. Однако, если уже есть путевая точка на
     * местоположение новой путевой точки, новая путевая точка заменяет только старую <em> only
     * if </ em> значение "предыдущей стоимости" новой путевой точки меньше текущей
     * значение «предыдущей стоимости» путевой точки.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
    	Waypoint oldWP = open_waypoint_list.get(newWP.getLocation());
    	if(oldWP == null) {
    		open_waypoint_list.put(newWP.getLocation(), newWP);
    		return true;
    	}else {
    		if(oldWP.getPreviousCost() > newWP.getPreviousCost()) {
    			open_waypoint_list.put(newWP.getLocation(), newWP);
    		}
    	}
    	return false;
    }


    /** Возвращает текущее количество открытых путевых точек. **/
    public int numOpenWaypoints()
    {
    	return open_waypoint_list.size();
    }


    /**
     * Этот метод перемещает путевую точку в указанном месте из
     * открыть список к закрытому списку.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint stream = open_waypoint_list.remove(loc);
        close_waypoint_list.put(loc, stream);
        
    }

    /**
     * Возвращает true, если коллекция закрытых путевых точек содержит путевую точку
     * для указанного места.
     **/
    public boolean isLocationClosed(Location loc)
    {
        if(close_waypoint_list.get(loc) != null) {
        	return true;
        }
        return false;
    }
}
