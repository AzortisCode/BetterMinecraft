/*
 * Copyright (C) 2019 Azortis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.azortis.betterminecraft.fastladder;

import com.azortis.betterminecraft.BetterMinecraft;
import com.azortis.betterminecraft.Module;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @version 1.0.0-SNAPSHOT
 */
public class FastLadder implements Module, Listener {

    @Override
    public Module load(BetterMinecraft plugin) {
        // Load anything needed here.
        Bukkit.getPluginManager().registerEvents(this, plugin);
        return this;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.LADDER) {
            event.getPlayer().teleport(findOpposite(event.getClickedBlock(), event.getPlayer().getLocation()));
        }
    }

    private Location findOpposite(Block b, Location loc) {
        Location temp = b.getLocation();
        temp.setX(temp.getBlockX());
        temp.setY(temp.getBlockY() - 1);
        temp.setZ(temp.getBlockZ());
        if (temp.getBlock().getType() == Material.LADDER) {
            while (temp.getBlock().getType() == Material.LADDER) {
                temp.setY(temp.getBlockY() - 1);
            }
            temp.setY(temp.getBlockY() + 1);
            loc.setY(temp.getY());
            return loc;
        } else {
            temp.setY(temp.getBlockY() + 2);
            if (temp.getBlock().getType() == Material.LADDER) {
                while (temp.getBlock().getType() == Material.LADDER) {
                    temp.setY(temp.getBlockY() + 1);
                }
                return temp;
            }
        }
        temp.setY(temp.getBlockY() + 1);
        loc.setY(temp.getY());
        return loc;
    }



}
