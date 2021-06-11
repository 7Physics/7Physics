package fr.setphysics.setphysics.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.World;

public class SimulationHandler implements ActionListener {
	private World world;
	private Timer timer;
	
	public SimulationHandler(World world) {
		this.world = world;
		this.timer = new Timer(1000 / 60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				world.step(1000/60);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String actionComm = arg0.getActionCommand();

		if (actionComm.equals("play")) {
			Logger.info("Lancement de la simulation. Rafraichissement: 60FPS");
			timer.restart();
		} else if (actionComm.equals("stop")) {
			Logger.info("Arrêt de la simulation.");
			timer.stop();
			//world.reset();
		} else {
			Logger.error("L'action " + actionComm + " n'est pas permise.");
		}
	}
}
