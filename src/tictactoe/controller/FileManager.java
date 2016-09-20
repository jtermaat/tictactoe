package tictactoe.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tictactoe.model.MoveSeries;

public class FileManager {
	
	public static String DEFAULT_FILENAME = "../failedMoves.txt";
	
		public List<MoveSeries> readFile(String file) {

			BufferedReader br = null;
			List<MoveSeries> allMoveSets = new ArrayList<MoveSeries>();

			try {

				String sCurrentLine;

				br = new BufferedReader(new FileReader(file));

				while ((sCurrentLine = br.readLine()) != null) {
					if (!sCurrentLine.trim().isEmpty()) {
						MoveSeries series = MoveSeries.buildFromString(sCurrentLine);
						allMoveSets.add(series);
					}
				}

			} catch (IOException e) {
				return allMoveSets;
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return allMoveSets;
	}
		
	public void writeMovesToFile(String filename, MoveSeries series, boolean tied) {
			try {

				String content = series.toString();
				// Remove player's final move.
		
				content = removeFinalMove(content);
				if (tied) {
					content = removeFinalMove(content); // Remove last two moves in a tie.
				}

				File file = new File(filename);

				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.newLine();
				bw.append(content);
				bw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		

	}
	
	public String removeFinalMove(String content) {
		return content.substring(0,content.lastIndexOf("-"));
	}
}




