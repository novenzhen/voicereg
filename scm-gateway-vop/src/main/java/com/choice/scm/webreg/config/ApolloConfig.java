package com.choice.scm.webreg.config;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;


/**
 * @author Jason Song(song_s@ctrip.com)
 */

@Configuration
@EnableApolloConfig(value = "application", order = 10)
public class ApolloConfig {
}

