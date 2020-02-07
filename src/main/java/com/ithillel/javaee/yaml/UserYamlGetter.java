package com.ithillel.javaee.yaml;

import com.ithillel.javaee.model.User;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class UserYamlGetter implements YamlGetter<User.UserParameters> {
    private final Yaml yaml = new Yaml(new Constructor(User.UserParameters.class));

    @Override
    public User.UserParameters getParameters(String resourceName) {
        return yaml.load(this.getClass()
                .getClassLoader()
                .getResourceAsStream(resourceName));
    }
}
