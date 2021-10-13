package com.denglitong.readinglist.config;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * 条件化配置
 * 当JdbcTemplateCondition.class条件成立时才会成立
 * <p>
 * \@ConditionalOnBean
 * \@ConditionalOnMissingBean
 * \@ConditionalOnClass
 * \@ConditionalOnMussingClass
 * \@ConditionalOnExpression
 * \@ConditionalOnJava
 * \@ConditionalOnProperty
 * \@ConditionalOnResource
 * \@ConditionalOnWebApplication
 * \@ConditionalOnNotWebApplication
 *
 * @author litong.deng@foxmail.com
 * @date 2021/10/13
 */
@Conditional(JdbcTemplateCondition.class)
@Service
public class MyService {
}
