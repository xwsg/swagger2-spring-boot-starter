package io.github.xwsg.swagger;


import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger2 properties.
 *
 * @author wsg
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2Properties {

    /**
     * Api信息
     */
    private ApiInfo apiInfo = new ApiInfo();

    /**
     * swagger解析包路径
     */
    private List<String> basePackage = new ArrayList<>();

    /**
     * swagger解析URL规则,ANT
     */
    private List<String> basePath = new ArrayList<>();

    /**
     * swagger排除解析URL规则,ANT
     */
    private List<String> excludePath = new ArrayList<>();

    /**
     * 全局参数配置
     */
    private List<Parameter> globalOperationParameter = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class ApiInfo {

        /**
         * 标题
         */
        private String title = "";

        /**
         * 描述
         */
        private String description = "";

        /**
         * 服务条款URL
         */
        private String termsOfServiceUrl = "";

        /**
         * 联系人信息
         */
        private Contact contact = new Contact();

        /**
         * 许可证
         */
        private String license = "";

        /**
         * 许可证URL
         */
        private String licenseUrl = "";

        /**
         * 版本
         */
        private String version = "";

        @Data
        @NoArgsConstructor
        public static class Contact {

            /**
             * 联系人
             */
            private String name = "";

            /**
             * 联系人URL
             */
            private String url = "";

            /**
             * 联系人邮箱
             */
            private String email = "";
        }
    }

    @Data
    @NoArgsConstructor
    public static class Parameter {

        /**
         * 全局参数名
         */
        private String name = "";

        /**
         * 全局参数描述
         */
        private String description = "";

        /**
         * 全局参数默认值
         */
        private String defaultValue = "";

        /**
         * 是否必须
         */
        private Boolean required = true;

        /**
         * 参数数据类型
         */
        private String modelRef = "string";

        /**
         * 参数类型,可选 query, path, body, header
         */
        private String paramType = "query";
    }
}
