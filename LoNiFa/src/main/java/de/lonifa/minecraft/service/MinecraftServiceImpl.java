package de.lonifa.minecraft.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MinecraftServiceImpl implements MinecraftService {
	private Path filePath;

	MinecraftServiceImpl() {
		filePath = Paths.get("D:\\Server\\minecraft\\logs\\latest.log");
		//filePath = Paths.get("C:\\Users\\lukeh\\Desktop\\minecraft server test\\logs\\latest.log");
	}

	@Override
	public List<String> getConsole() {
		try {
			List<String> lines = Files.readAllLines(filePath, StandardCharsets.ISO_8859_1);
			return lines;
		} catch (IOException e) {
			System.err.println("error while reading minecraft console log");
			return Collections.emptyList();
		}
	}
}
