package com.github.lordlothar99.random.api;

import com.github.lordlothar99.random.RandomGeneratorsRegistry;

public interface RegistryAware {

	void setRegistry(RandomGeneratorsRegistry registry);

	RandomGeneratorsRegistry getRegistry();
}
