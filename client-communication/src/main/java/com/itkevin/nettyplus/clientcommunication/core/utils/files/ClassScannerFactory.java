package com.itkevin.nettyplus.clientcommunication.core.utils.files;

import com.itkevin.nettyplus.clientcommunication.core.utils.SystemUtils;

/**
 * Created by wken on 16/6/13.
 */
public class ClassScannerFactory {
	
	private static final ClassScannerFactory INSTANCE = new ClassScannerFactory();

	private IClassScanner classScanner;

	private ClassScannerFactory() {

	}

	public static ClassScannerFactory getInstance() {
		return INSTANCE;
	}

	public IClassScanner classScanner(String scannerPackage) {
		if (classScanner == null) {
			synchronized (ClassScannerFactory.class) {
				if (this.classScanner == null) {
					this.classScanner = produceClassScanner(scannerPackage);
				}
			}
		}

		return this.classScanner;
	}

	private IClassScanner produceClassScanner(String scannerPackage) {
		IClassScanner scanner;
		if (SystemUtils.isMacOs() || SystemUtils.isWinOs() || "dev".equals(System.getProperty("spring.profiles.active"))) {
			scanner = new JarClassScanner(scannerPackage);
		} else {
			scanner = new DexJarClassScanner(scannerPackage);
		}
		return scanner;
	}
    
}
