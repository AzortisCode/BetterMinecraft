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

package com.azortis.enhancedgameplay.fastladder;

import com.azortis.enhancedgameplay.EnhancedGameplay;
import com.azortis.enhancedgameplay.Module;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Ladder;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 * @version 1.0.0-SNAPSHOT
 */
public class FastLadder implements Module, Listener {

    @Override
    public Module load(EnhancedGameplay plugin) {
        // Load anything needed here.
        Bukkit.getPluginManager().registerEvents(this, plugin);
        return this;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.LADDER) {
            Location l = findOpposite(event.getClickedBlock(), event.getPlayer().getLocation());
            event.getPlayer().teleport(l, PlayerTeleportEvent.TeleportCause.PLUGIN);

        }
    }

    private Location findOpposite(Block b, Location loc) {
        Location temp = b.getLocation();
        if (temp.getBlock().getType() == Material.LADDER) {
            loc.setX(b.getX() + 0.5);
            loc.setZ(b.getZ() + 0.5);
            temp.setY(temp.getBlockY() - 1);
            if (temp.getBlock().getType() == Material.LADDER) {
                while (temp.getBlock().getType() == Material.LADDER) {
                    temp.setY(temp.getBlockY() - 1);
                }
                loc.setY(temp.getBlockY() + 1);
                return loc;
            }
            temp.setY(temp.getBlockY() + 2);
            while (temp.getBlock().getType() == Material.LADDER) {
                temp.setY(temp.getBlockY() + 1);
            }
            loc.setY(temp.getBlockY());
            return loc;
        }
        return loc;
    }

}
