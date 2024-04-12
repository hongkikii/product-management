package kr.co.hanbit.product.management;

import java.sql.Connection;
import javax.sql.DataSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);
		return modelMapper;
	}

	@Bean
	public ApplicationRunner runner(DataSource dataSource) {
		return args -> {
			// 커넥션풀은 처음 사용되는 시점에 생성
			Connection connection = dataSource.getConnection();
		};
	}
}
