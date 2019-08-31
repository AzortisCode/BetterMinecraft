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
 */

package com.azortis.betterminecraft;

import com.azortis.betterminecraft.fastladder.FastLadder;
import com.azortis.betterminecraft.settings.SettingsManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class BetterMinecraft extends JavaPlugin {
    private SettingsManager settingsManager;
    private Set<Module> modules = new HashSet<>();

    @Override
    public void onEnable() {


        // Initialize Settings
        settingsManager = new SettingsManager(this);

        // Initialize Modules
        initModules();

    }

    @Override
    public void onDisable() {
        // todo : Unload modules.
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    private void initModules() {
        settingsManager.getModuleSettings().getEnabledModules().iterator().forEachRemaining(s -> {
            if ("FastLadder".equals(s)) {
                modules.add(new FastLadder().load(this));
            }
        });
    }
}
