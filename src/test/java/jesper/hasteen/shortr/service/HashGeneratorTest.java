package jesper.hasteen.shortr.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HashGeneratorTest {

    final HashGenerator hashGenerator = new HashGenerator();

    @Test
    void same_string_always_gives_the_same_hash() {
        final String s1 = hashGenerator.generate("ohafjhas\nfjhk\tjasf");
        final String s2 = hashGenerator.generate("ohafjhas\nfjhk\tjasf");

        assertEquals(7, s1.length());
        assertEquals(s1, s2);
    }

    @Test
    void different_strings_dont_give_same_hash() {
        final String s1 = hashGenerator.generate("ohafjhasfjhkjasf");
        final String s2 = hashGenerator.generate("ohafjhasfjhkjasr");

        assertEquals(7, s1.length());
        assertNotEquals(s1, s2);
    }

    @Test
    void can_handle_long_strings() {
        String s = "In elementum lacus elit, ac scelerisque purus aliquet quis. Morbi a diam et lacus mollis accumsan" +
                " vel vitae magna. Cras efficitur velit et risus tincidunt, at facilisis felis laoreet. Nam commodo" +
                " mauris risus, eu hendrerit justo cursus et. Curabitur neque dolor, dapibus et nisl id, imperdiet" +
                " congue ex. Curabitur congue lorem gravida ex rutrum fringilla. Quisque magna nulla, rutrum eu" +
                " vestibulum eu, bibendum ut tellus. In sapien mauris, congue eget lacinia ullamcorper, hendrerit" +
                " at enim. Nunc varius quam nec dui commodo, a mattis eros sollicitudin. Sed facilisis, nibh eget" +
                " pulvinar rutrum, erat lectus malesuada tortor, eu dictum orci metus feugiat arcu. Morbi aliquet" +
                " mollis ligula, eget vestibulum justo malesuada et. Vivamus egestas dolor vitae tempor faucibus." +
                " Etiam scelerisque malesuada efficitur. Sed egestas sollicitudin nisi, nec imperdiet nulla." +
                " Proin a feugiat erat, at hendrerit lorem.";
        final String s1 = hashGenerator.generate(s + s + s + s);
        final String s2 = hashGenerator.generate(s + s + s + s);

        assertEquals(7, s1.length());
        assertEquals(s1, s2);
    }
}