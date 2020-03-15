package com.cloud.storage;

import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojing
 */
@RestController
public class StorageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageController.class);

	private static final String SUCCESS = "SUCCESS";

	private static final String FAIL = "FAIL";

	private final JdbcTemplate jdbcTemplate;

	public StorageController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping(value = "/storage/{commodityCode}/{count}", produces = "application/json")
	public String echo(@PathVariable String commodityCode, @PathVariable int count) {
		LOGGER.info("Storage Service Begin ... xid: " + RootContext.getXID());
		int result = jdbcTemplate.update(
				"update storage_tbl set count = count - ? where commodity_code = ?",
				new Object[] { count, commodityCode });
		LOGGER.info("Storage Service End ... ");
		if (result == 1) {
			return SUCCESS;
		}
		return FAIL;
	}

}
