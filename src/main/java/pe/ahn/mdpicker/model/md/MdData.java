package pe.ahn.mdpicker.model.md;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MdData {
    private String brand;
    private int top;
    private int outer;
    private int jeans;
    private int sneakers;
    private int bag;
    private int cap;
    private int socks;
    private int accessories;
}
