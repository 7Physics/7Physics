package fr.setphysics.setphysics.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.World;

public class SimulationLauncher implements ActionListener {
	private World world;
	
	public SimulationLauncher(World world) {
		this.world = world;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Logger.info("Lancement de la simulation. Rafraichissement: 60FPS");
		final long startTime = new Date().getTime();
		Timer timer = new Timer(1000 / 60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				world.step(new Date().getTime() - startTime);
			}

		});
		timer.start();
	}
			

}