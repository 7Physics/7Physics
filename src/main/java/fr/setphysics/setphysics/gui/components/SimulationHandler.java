package fr.setphysics.setphysics.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.World;

public class SimulationHandler implements ActionListener {
	private World world;

	public SimulationHandler(World world) {
		this.world = world;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String actionComm = arg0.getActionCommand();

		if (actionComm.equals("play")) {
			Logger.info("Lancement de la simulation. Rafraichissement: 60FPS");
			world.startStepLoop(60);
		} else if (actionComm.equals("stop")) {
			Logger.info("Arrêt de la simulation.");
			world.stopStepLoop();
			world.reset();
		} else {
			Logger.error("L'action " + actionComm + " n'est pas permise.");
		}
	}
}
